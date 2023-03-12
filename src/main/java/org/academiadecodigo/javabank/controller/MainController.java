package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.service.AuthService;
import org.academiadecodigo.javabank.view.MainView;
import org.academiadecodigo.javabank.view.Messages;
import org.academiadecodigo.javabank.view.UserOptions;

import java.util.Map;

/**
 * The {@link MainView} controller
 */
public class MainController extends AbstractController {

    private Map<Integer, Controller> controllerMap;

    /**
     * Sets the controller map, responsible for the mapping between the menu options and the respective controller
     *
     * @param controllerMap the controller map to set
     */
    public void setControllerMap(Map<Integer, Controller> controllerMap) {
        this.controllerMap = controllerMap;
    }

    /**
     * Invokes the next controller based on menu selection
     *
     * @param option the option chosen
     */
    public void onMenuSelection(int option) {

        if (option == UserOptions.QUIT.getOption()) {
            return;
        }

        if (!controllerMap.containsKey(option)) {
            throw new IllegalStateException(Messages.SYSTEM_ERROR);
        }

        controllerMap.get(option).init();
        init();

    }

    public Customer getAccessingCustomer() {
        return authService.getAccessingCustomer();
    }



}