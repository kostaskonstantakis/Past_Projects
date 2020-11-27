/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

/**
 *
 * @author gussl
 */
import com.google.gson.Gson;
/*import gr.csd.uoc.cs359.winter2019.logbook.model.User;
import gr.csd.uoc.cs359.winter2019.logbook.model.UserContainer;
import gr.csd.uoc.cs359.winter2019.logbook.model.Post;
import gr.csd.uoc.cs359.winter2019.logbook.model.PostContainer;*/
import gr.csd.uoc.cs359.winter2019.logbook.model.*;
import static spark.Spark.*;
//import spark.servlet.SparkApplication;

public class sparkREST {

    private static final UserContainer ucontainer = new UserContainer();
    private static final PostContainer pcontainer = new PostContainer();


    public static void main(String[] args) {

        //port(5678); //default port is 4567
        //User REST stuff
        // Adding  a new user
        post("/user", (request, response) -> {
            response.type("application/json");
            User u = new User();
            u = new Gson().fromJson(request.body(), u.getClass());

            if (ucontainer.addUser(u)) {
                response.status(201);
                return new Gson()
                        .toJson(new UserResponse(UserResponse.ResponseEnum.SUCCESS,
                                "User Added",
                                new Gson().toJsonTree(u)));
            } else {
                response.status(400);
                return new Gson()
                        .toJson(new UserResponse(UserResponse.ResponseEnum.ERROR,
                                "Wrong User Description",
                                new Gson().toJsonTree(u)));
            }
        });

        // Wrong user request
        post("/user/:id", (request, response) -> {
            response.type("application/json");
            response.status(405);

            // We could also return supported methods for this resource...!!!
            return new Gson()
                    .toJson(new UserResponse(UserResponse.ResponseEnum.ERROR,
                            "POST not supported in non container URIs",
                            new Gson().toJsonTree("{}")));
        });

        // TODO Implementation should return the representation of the user container
        get("/user", (request, response) -> {
            response.status(200);
            return new Gson()
                    .toJson(new UserContainerResponse(UserContainerResponse.ResponseEnum.SUCCESS,
                            "User in container",
                            new Gson().toJsonTree(ucontainer)));
        });

        // Get representation of user
        get("/user/:id", (request, response) -> {

            response.type("application/json");
            User u = ucontainer.getUser(request.params(":id"));
            if (u != null) {
                response.status(200);
                return new Gson()
                        .toJson(new UserResponse(UserResponse.ResponseEnum.SUCCESS,
                                "User Retrieved",
                                new Gson().toJsonTree(u)));
            } else {
                response.status(404);
                return new Gson()
                        .toJson(new UserResponse(UserResponse.ResponseEnum.ERROR,
                                "",
                                new Gson().toJsonTree("")));
            }
        });

        // TODO Updating a user resource
        put("/user/:id", (request, response) -> {

            String id = request.params(":id");
            User u = ucontainer.getUser(id);
            if (u != null) {
                String userName = request.queryParams("userName");
                String email = request.queryParams("email");
                String password = request.queryParams("password");;    // (could be encrypted in md5)
                String firstName = request.queryParams("firstName");
                String lastName = request.queryParams("lastName");
                String birthDate = request.queryParams("birthDate");
                String country = request.queryParams("country");
                String address = request.queryParams("address");
                String occupation = request.queryParams("occupation");
                String interests = request.queryParams("interests");
                String town = request.queryParams("town");
                String info = request.queryParams("info");

                if (!userName.equals(null) && !email.equals(null) && !password.equals(null)
                        && !firstName.equals(null) && !lastName.equals(null) && !birthDate.equals(null)
                        && !country.equals(null) && !address.equals(null) && !occupation.equals(null)
                        && !interests.equals(null) && !town.equals(null) && !info.equals(null)) {

                    u.setAddress(address);
                    u.setBirthDate(birthDate);
                    u.setCountry(country);
                    u.setEmail(email);
                    u.setFirstName(firstName);
                    u.setInfo(info);
                    u.setInterests(interests);
                    u.setOccupation(occupation);
                    u.setLastName(lastName);
                    u.setPassword(password);
                    u.setTown(town);
                    u.setUserName(userName);
                }
                response.status(200);
                return new Gson().toJson(new UserResponse(UserResponse.ResponseEnum.SUCCESS,
                        "User " + id + " updated",
                        new Gson().toJsonTree(u)));
            } else {
                response.status(404); // 404 Not found
                return new Gson().toJson(new UserResponse(UserResponse.ResponseEnum.ERROR,
                        "User with " + id + " not found", new Gson().toJsonTree(u)));
            }

        });

        // TODO deleting a user resource
        delete("/user/:id", (request, response) -> {
            String id = request.params(":id");
            if (ucontainer.exists(id)) {
                ucontainer.deleteUser(id);

            response.status(200);
                return new Gson().toJson(new UserResponse(UserResponse.ResponseEnum.SUCCESS,
                        "User " + id + " deleted",
                        new Gson().toJsonTree("")));
            } else {
                response.status(404); // 404 Not found
                return new Gson().toJson(new UserResponse(UserResponse.ResponseEnum.ERROR,
                        "User with " + id + " not found", new Gson().toJsonTree(ucontainer)));
            }

        });

        // TODO Options (ask about existential things)
        options("/user/:id", (request, response) -> {
            return new Gson().toJson(new UserResponse(UserResponse.ResponseEnum.SUCCESS, "", new Gson().toJsonTree("")));
        });

        // TODO Options, return supported actions
        options("/user/", (request, response) -> {
            return new Gson().toJson(new UserResponse(UserResponse.ResponseEnum.SUCCESS,
                    "PUT /user/:id updates a user resource.\nDELETE /user/:id deletes a user resource.\n"
                    + "POST /user adds a new user resource.\n" + "POST /user/:id handles wrong user request.\n"
                    + "GET /user returns a representation of the user resource.\n"
                    + "GET /user/:id returns a representation of a user.\n"
                    + "OPTIONS /user/ prints all the possible actions you're allowed to do with the user resource",
                    new Gson().toJsonTree("")));
        });

        //Post REST stuff
        // Adding  a new post
        post("/posts", (request, response) -> {
            response.type("application/json");
            Post p = new Post();
            p = new Gson().fromJson(request.body(), p.getClass());

            if (pcontainer.addPost(p)) {
                response.status(201);
                return new Gson()
                        .toJson(new PostResponse(PostResponse.ResponseEnum.SUCCESS,
                                "Post Added",
                                new Gson().toJsonTree(p)));
            } else {
                response.status(400);
                return new Gson()
                        .toJson(new PostResponse(PostResponse.ResponseEnum.ERROR,
                                "Wrong Post Description",
                                new Gson().toJsonTree(p)));
            }
        });

        // Wrong post request
        post("/posts/:id", (request, response) -> {
            response.type("application/json");
            response.status(405);

            // We could also return supported methods for this resource...!!!
            return new Gson()
                    .toJson(new PostResponse(PostResponse.ResponseEnum.ERROR,
                            "POST not supported in non container URIs",
                            new Gson().toJsonTree("{}")));
        });

        // TODO Implementation should return the representation of the post container
        get("/posts", (request, response) -> {
            response.status(200);
            return new Gson()
                    .toJson(new PostContainerResponse(PostContainerResponse.ResponseEnum.SUCCESS,
                            "Posts in container",
                            new Gson().toJsonTree(pcontainer)));
        });

        // Get representation of a post
        get("/posts/:id", (request, response) -> {

            response.type("application/json");
            Post p = pcontainer.getPost(request.params(":id"));
            if (p != null) {
                response.status(200);
                return new Gson()
                        .toJson(new PostResponse(PostResponse.ResponseEnum.SUCCESS,
                                "Post Retrieved",
                                new Gson().toJsonTree(p)));
            } else {
                response.status(404);
                return new Gson()
                        .toJson(new PostResponse(PostResponse.ResponseEnum.ERROR,
                                "",
                                new Gson().toJsonTree("")));
            }
        });

        // TODO Updating a post resource
        put("/posts/:id", (request, response) -> {

            String id = request.params(":id");
            Post p = pcontainer.getPost(id);
            if (p != null) {
                String userName = request.queryParams("userName");
                String description = request.queryParams("description");
                String resourceURL = request.queryParams("resourceURL");;    // (could be encrypted in md5)
                String imageURL = request.queryParams("imageURL");
                String imageBase64 = request.queryParams("imageBase64");
                String latitude = request.queryParams("latitude");
                String longitude = request.queryParams("longitude");
                String createdAt = request.queryParams("createdAt");

                if (!userName.equals(null) && !description.equals(null) && !resourceURL.equals(null)
                        && !imageURL.equals(null) && !imageBase64.equals(null) && !latitude.equals(null)
                        && !longitude.equals(null) && !createdAt.equals(null)) {

                    p.setCreatedAt(createdAt);
                    p.setDescription(description);
                    p.setUserName(userName);
                    p.setImageBase64(imageBase64);
                    p.setImageURL(imageURL);
                    p.setLatitude(latitude);
                    p.setLongitude(longitude);
                    p.setResourceURL(resourceURL);
                }
                response.status(200);
                return new Gson()
                        .toJson(new PostResponse(PostResponse.ResponseEnum.SUCCESS,
                                "Post updated",
                                new Gson().toJsonTree(p)));
            } else {
                response.status(404); // 404 Not found
                return new Gson().toJson(new PostResponse(PostResponse.ResponseEnum.ERROR,
                        "Post with " + id + " not found", new Gson().toJsonTree(p)));
            }

        });

        // TODO deleting a post resource
        delete("/posts/:id", (request, response) -> {
            String id = request.params(":id");
            if (pcontainer.exists(id)) {
                pcontainer.deletePost(id);

                response.status(200);
                return new Gson().toJson(new PostResponse(PostResponse.ResponseEnum.SUCCESS,
                        "Post with " + id + " deleted",
                        new Gson().toJsonTree("")));

            } else {
                response.status(404); // 404 Not found
                return new Gson().toJson(new PostResponse(PostResponse.ResponseEnum.ERROR,
                        "Post with " + id + " not found", new Gson().toJsonTree(pcontainer)));
            }

        });

        // TODO Options (ask about existential things)
        options("/posts/:id", (request, response) -> {

            return new Gson().toJson(new PostResponse(PostResponse.ResponseEnum.SUCCESS, "", new Gson().toJsonTree("")));
        });

        // TODO Options, return supported actions
        options("/posts/", (request, response) -> {
            return new Gson().toJson(new PostResponse(PostResponse.ResponseEnum.SUCCESS,
                    "PUT /posts/:id updates a post resource.\nDELETE /posts/:id deletes a post resource.\n"
                    + "POST /posts adds a new post resource.\n" + "POST /posts/:id handles wrong post request.\n"
                    + "GET /posts returns a representation of the post resource.\n"
                    + "GET /posts/:id returns a representation of a post.\n"
                    + "OPTIONS /posts/ prints all the possible actions you're allowed to do with the post resource.\n",
                    new Gson().toJsonTree("")));
        });

    }

}
