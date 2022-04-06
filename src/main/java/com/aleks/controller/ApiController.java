package com.aleks.controller;

import com.aleks.model.Player;
import com.aleks.repository.GameplayRepository;
import com.aleks.repository.PlayerRepository;
import com.aleks.services.Game;
import com.aleks.utils.AdapterField;
import com.aleks.utils.StandardResponse;
import com.aleks.utils.StatusResponse;
import com.google.gson.Gson;

import static spark.Spark.*;

public class ApiController extends Controller {
    // данные для запуска сервера
    String JSON_MIME_TYPE = "application/json";
    String SERVER_HOST = "127.0.0.1";
    int SERVER_PORT = 8080;

    @Override
    public void start() {
        ipAddress(SERVER_HOST);
        port(SERVER_PORT);
        get("/gameplay", (request, response) -> { // Возвращаем рейтинг игроков
            dbH2 = new PlayerRepository();
            dbH2.show();
            return new Gson().toJson(new StandardResponse(StatusResponse.OK, new Gson().toJsonTree(dbH2.getRating())));
        });
        post("/gameplay", (request, response) -> { // Добавляем игроков
            String nameX = request.queryParams("nameX");
            String name0 = request.queryParams("name0");
            if (nameX == null || name0 == null) {
                response.status(StatusResponse.BAD_REQUEST.getStatus());
            } else {
                dbH2 = new PlayerRepository();
                dbH2.setPlayerX(new Player(nameX, 'X', "1"));
                dbH2.setPlayer0(new Player(name0, '0', "2"));
                dbH2.add();
                dbH2 = new GameplayRepository();
                dbH2.setPlayerX(new Player(nameX, 'X', "1"));
                dbH2.setPlayer0(new Player(name0, '0', "2"));
            }
            return new Gson().toJson(response.status());
        });
        put("/gameplay", (request, response) -> { // Редактируем игровое поле
            dbH2.create();
            System.out.println(dbH2.getPlayerX());
            String[] winOrDraw = new Game().changeField(request.queryParams("step"),
                    request.queryParams("name").equals(dbH2.getPlayerX().getName()) ?
                            dbH2.getPlayerX() : dbH2.getPlayer0(), AdapterField.adapterField(dbH2.getArray().toCharArray()));
            dbH2.setArray(winOrDraw[0]);
            dbH2.add();
            if (winOrDraw[1] == null) {
                return new Gson().toJson(new StandardResponse(StatusResponse.OK));
            } else {
                if (!winOrDraw[1].equals("Draw!")) new PlayerRepository().addPoint(winOrDraw[1]);
                return new Gson().toJson(new StandardResponse(StatusResponse.OK, new Gson().toJsonTree(winOrDraw)));
            }
        });
        delete("/gameplay", (request, response) -> { // Удаляем таблицу players
            dbH2 = new PlayerRepository();
            dbH2.delete();
            return new Gson().toJson(new StandardResponse(StatusResponse.OK));
        });
        after((req, res) -> res.type(JSON_MIME_TYPE));
    }
}
