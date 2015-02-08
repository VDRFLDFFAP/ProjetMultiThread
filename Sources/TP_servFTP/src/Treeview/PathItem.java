package Treeview;

import java.nio.file.Path;

/**
 * Created by valentindufermont on 26/11/2014.
 */
public class PathItem {
    private Path path;
    public PathItem(Path path) {
        this.path = path;
    }
    public Path getPath() {
        return path;
    }
    @Override
    public String toString() {
        if (path.getFileName() == null) {
            return path.toString();
        } else {
            return path.getFileName().toString(); // showing file name on the TreeView
        }
    }
}