package com.kodcu.service;

import com.kodcu.controller.ApplicationController;
import com.kodcu.other.Current;
import com.kodcu.service.ui.FileBrowseService;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by usta on 25.12.2014.
 */
@Component
public class DirectoryService {

    private final ApplicationController controller;
    private final FileBrowseService fileBrowser;
    private final Current current;
    
    private Optional<Path> workingDirectory = Optional.of(Paths.get(System.getProperty("user.home")));
    private Optional<File> initialDirectory = Optional.empty();

    private Supplier<Path> workingDirectorySupplier;
    private Consumer<Path> openFileConsumer;
    private Supplier<Path> pathSaveSupplier;

    
    @Autowired
    public DirectoryService(final ApplicationController controller, final FileBrowseService fileBrowser, final Current current) {
        this.controller = controller;
        this.fileBrowser = fileBrowser;
        this.current = current;
        
         workingDirectorySupplier = () -> {
            final DirectoryChooser directoryChooser = newDirectoryChooser("Выберите рабочую директорию");
            final File file = directoryChooser.showDialog(null);

            workingDirectory = Optional.ofNullable(file.toPath());

            workingDirectory.ifPresent(path -> fileBrowser.browse(controller.getTreeView(), path));

            return Objects.nonNull(file) ? file.toPath() : null;
        };
        
//        openFileConsumer = path -> {
//            if (Files.isDirectory(path)) {
//                changeWorkigDir(path.equals(workingDirectory()) ? path.getParent() : path);
//            } else if (pathResolver.isAsciidoc(path) || pathResolver.isMarkdown(path))
//                tabService.addTab(path);
//            else if (pathResolver.isImage(path))
//                tabService.addImageTab(path);
//            else if (pathResolver.isEpub(path))
//                controller.getHostServices()
//                .showDocument(String.format("http://localhost:%d/epub/viewer?path=%s", controller.getPort(), path.toString()));
//            else
//                controller.getHostServices()
//                .showDocument(path.toUri().toString());
//        };

        pathSaveSupplier = () -> {
            final FileChooser chooser = newFileChooser("Сохранить документ");
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Asciidoc", "*.asc", "*.asciidoc", "*.adoc", "*.ad", "*.txt","*.*"));
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Markdown", "*.md", "*.markdown", "*.txt","*.*"));
            File file = chooser.showSaveDialog(null);
            return Objects.nonNull(file) ? file.toPath() : null;
        };

    }

    public DirectoryChooser newDirectoryChooser(String title) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(title);
        initialDirectory.ifPresent(file -> {
            if (Files.isDirectory(file.toPath()))
                directoryChooser.setInitialDirectory(file);
            else
                directoryChooser.setInitialDirectory(file.toPath().getParent().toFile());
        });
        return directoryChooser;
    }

    public FileChooser newFileChooser(String title) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        initialDirectory.ifPresent(file -> {
            if (Files.isDirectory(file.toPath()))
                fileChooser.setInitialDirectory(file);
            else
                fileChooser.setInitialDirectory(file.toPath().getParent().toFile());
        });

        return fileChooser;
    }

    public Path workingDirectory() {
        return workingDirectory.orElseGet(workingDirectorySupplier);
    }

    public Path currentPath() {
        return current.currentPath().orElseGet(pathSaveSupplier);
    }

    public Supplier<Path> getWorkingDirectorySupplier() {
        return workingDirectorySupplier;
    }

    public void setWorkingDirectorySupplier(Supplier<Path> workingDirectorySupplier) {
        this.workingDirectorySupplier = workingDirectorySupplier;
    }

    public Consumer<Path> getOpenFileConsumer() {
        return openFileConsumer;
    }

    public void setOpenFileConsumer(Consumer<Path> openFileConsumer) {
        this.openFileConsumer = openFileConsumer;
    }

    public Supplier<Path> getPathSaveSupplier() {
        return pathSaveSupplier;
    }

    public void setPathSaveSupplier(Supplier<Path> pathSaveSupplier) {
        this.pathSaveSupplier = pathSaveSupplier;
    }

    public void setWorkingDirectory(Optional<Path> workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    public Optional<Path> getWorkingDirectory() {
        return workingDirectory;
    }

    public Optional<File> getInitialDirectory() {
        return initialDirectory;
    }

    public void setInitialDirectory(Optional<File> initialDirectory) {
        this.initialDirectory = initialDirectory;
    }

    public void changeWorkigDir() {
        DirectoryChooser directoryChooser = this.newDirectoryChooser("Выберите рабочую директорию");
        File selectedDir = directoryChooser.showDialog(null);
        if (Objects.nonNull(selectedDir)) {
            controller.getRecentFiles().setWorkingDirectory(selectedDir.toString());
            this.setWorkingDirectory(Optional.of(selectedDir.toPath()));
            fileBrowser.browse(controller.getTreeView(), selectedDir.toPath());
            this.setInitialDirectory(Optional.ofNullable(selectedDir));

        }
    }

    public void changeWorkigDir(Path path) {
        if (Objects.isNull(path))
            return;
        controller.getRecentFiles().setWorkingDirectory(path.toString());
        this.setWorkingDirectory(Optional.of(path));
        fileBrowser.browse(controller.getTreeView(), path);
        this.setInitialDirectory(Optional.ofNullable(path.toFile()));

    }

    public void goUp() {
        workingDirectory.map(Path::getParent).ifPresent(this::changeWorkigDir);
    }

    public void refreshWorkingDir() {
        workingDirectory.ifPresent(this::changeWorkigDir);
    }
}
