package org.organizer;

import org.organizer.view.OrganizerView;
import org.organizer.controller.OrganizerController;
import org.organizer.model.ItemList;

/**
 * This class represents the main class of the Organizer application.
 *
 * @version 1.3
 */
public class Main
{
    /**
     * The main method of the Organizer application.
     * It creates the view, model and controller, and runs the application.
     * Method could work without arguments.
     * <p>
     * If arguments are provided, it checks for the "-o" flag followed by a file path and an optional "-e" flag.
     * If "-o" and file path are present, it attempts to load data from the specified file.
     * If the "-e" flag is provided and the file cannot be loaded, the program will exit.
     * <p>
     * Parameters order: [-o filePath [-e]]
     *
     * @param args Command line arguments. If provided, the first argument should be "-o",
     *             the second argument should be a file path, and an optional third argument can be "-e".
     */
    public static void main(String[] args) {
        // Create the view, model and controller
        OrganizerView view = new OrganizerView();
        ItemList itemList = new ItemList();
        OrganizerController controller = new OrganizerController(view, itemList);
        // Add sample data
        controller.addSampleData();
        // If arguments are provided, check for the "-o" flag followed by a file path and an optional "-e" flag
        if (args.length > 1 && args.length < 4) {
            if (args[0].equals("-o")) {
                if (args.length > 2 && args[2].equals("-e")) {
                    if (!controller.loadDataFromFile(args[1]))
                        return;
                    controller.loadDataFromFile(args[1]);
                }
            }
        }
        // Run the application
        while (controller.runApp()) ;
    }
}