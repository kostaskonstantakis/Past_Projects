/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.csd.uoc.cs359.winter2019.logbook.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author gussl
 */
public class UserContainer {

    Map<String, User> map = new HashMap<>();

    public boolean addUser(User u) {
        if ((u == null)) {
            return false;
        }

        String uniqueID = UUID.randomUUID().toString();
        u.setUserID(Integer.parseInt(uniqueID));
        map.put(u.getUserID().toString(), u);
        return true;
    }

    public Map<String, User> getUsers() {
        return map;
    }

    public User getUser(String id) {
        return map.get(id);
    }

    public User editUser(User u) {
        map.put(u.getUserID().toString(), u);
        return u;
    }

    public void deleteUser(String id) {
        map.remove(id);
    }

    public boolean exists(String id) {
        return map.containsKey(id);
    }

}
