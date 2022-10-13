/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Request;
import entity.User;
import flag.ActionFlags;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DUC
 */
public class UserController {

    private final ObjectOutputStream objectOutputStream;

    public List<Integer> listRoomOpened = new ArrayList<>();

    public UserController(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    private void send(Request request) {
        try {
            objectOutputStream.writeObject(request);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void login(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Request request = new Request(ActionFlags.LOGIN, user);
        send(request);
    }

    public void register(User user) {
        Request request = new Request(ActionFlags.REGISTER, user);
        send(request);
    }

    public void geListUser() {
        Request request = new Request(ActionFlags.GET_ALL_USER, null);
        send(request);
    }

    public void logout() {
        Request request = new Request(ActionFlags.LOGOUT, null);
        send(request);
    }





}
