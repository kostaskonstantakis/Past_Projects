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
public class PostContainer {

    /**
     *
     * @author papadako
     */
    Map<String, Post> map = new HashMap<>();

    public boolean addPost(Post p) {
        if ((p == null) || (p.getDescription() == null)) {
            return false;
        }

        String uniqueID = UUID.randomUUID().toString();
        p.setPostID(Integer.parseInt(uniqueID));
        map.put(p.getPostID().toString(), p);
        return true;
    }

    public Map<String, Post> getPosts() {
        return map;
    }

    public Post getPost(String id) {
        return map.get(id);
    }

    public Post editPost(Post p) {
        map.put(p.getPostID().toString(), p);
        return p;
    }

    public void deletePost(String id) {
        map.remove(id);
    }

    public boolean exists(String id) {
        return map.containsKey(id);
    }

}
