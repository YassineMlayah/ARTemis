/*import static spark.Spark.*;
import main.java.services.UserServices;
import main.java.models.User;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Gson gson = new Gson();

        post("/login", (req, res) -> {
            User user = gson.fromJson(req.body(), User.class);
            User loggedInUser = userService.login(user.getEmail(), user.getPassword());
            if (loggedInUser != null) {
                res.status(200);
                return gson.toJson(loggedInUser);
            }
            res.status(401);
            return "Invalid credentials";
        });
    }
}*/
package main.java;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.net.URI;

public class Main {
    public static void main(String[] args) {
        ResourceConfig config = new ResourceConfig()
            .packages("main.java.controllers");  // Scan les contrôleurs
        GrizzlyHttpServerFactory.createHttpServer(URI.create("http://localhost:8080/"), config);
        System.out.println("Serveur démarré sur http://localhost:8080/");
    }
}

