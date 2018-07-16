package hot;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.JTextArea;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
@Lazy(false)  // БИН СОЗДАЕТСЯ СРАЗУ ПРИ СТАРТЕ ПРИЛОЖЕНИЯ
@DependsOn(value = {"actionFacade","hashTextGui"})
public class CheckReceiveTask {

    @Value("${folder}")
    private String folder;
    
    @Inject
    @Qualifier("loggerBean")
    private Logger logBean;        

    @PostConstruct
    public void afterBirn() {
        new File(folder).mkdirs();
    }

    // async-метод для получения задач
    @Async // СКАНИРУЕМ НА НОВОЕ ЗАДАНИЕ в отдельном потоке
    public void receiveTaskChecker(JTextArea area) {
        System.out.println("task checker potok = " + Thread.currentThread().getName());
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
            // сканируем на изменения соответствующий каталог для задачи номер 1:
            Path dir = FileSystems.getDefault().getPath(folder);
            WatchKey key = dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);
            // Экземпляры WatchKey  потокобезопасны.
            while (true) {
                key = watcher.take(); // блокирует поток пока не появится событие
                // есть также неблокирующий способ получения key:
                //key = watcher.poll(5, TimeUnit.SECONDS);
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                        // запускаем выполнение нужной задачи
                        Date dt = new Date();
                        area.append(dt.getHours()+":"+dt.getMinutes()+":"+dt.getSeconds()+" = File task created in " + folder + "\n");
                    }
                    if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                        // запускаем выполнение нужной задачи
                        Date dt = new Date();
                        area.append(dt.getHours()+":"+dt.getMinutes()+":"+dt.getSeconds()+" = File task deleted in " + folder + "\n");
                    }                    
                }
                key.reset();
            }
        } catch (IOException | InterruptedException ex) {
            logBean.log(Level.SEVERE, null, ex);
        }
    }

}
