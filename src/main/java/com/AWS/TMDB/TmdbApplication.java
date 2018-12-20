package com.AWS.TMDB;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@SpringBootApplication
@Controller
public class TmdbApplication {

    private String version = "0.2\n";
    private String description = "In order to use this service type [IP]:8761/[series name] in browser to search.";
    private TmdbController TMDB = new TmdbController();
    private String jsonInString;


    public static void main(String[] args) {
        SpringApplication.run(TmdbApplication.class, args);
    }


    @RequestMapping(value = "/")
    @ResponseBody
    private String emptyURL() {
        return "TMDB Microservice Version: " + version + '\n' + description;
    }

    @RequestMapping("/{searchInput}")
    private @ResponseBody
    String getAttr(@PathVariable(value = "searchInput") String input) {
        // Format String for http query
        String search = input.replaceAll(" ", "+");
        search = search.replaceAll("%", "+");

        // Download JSON with query
        JSONArray resultJSONArray = TMDB.resultJSONObject(search);
        // Form to string
        jsonInString = resultJSONArray.toString();

		/* TODO Testing this:
		System.out.println(jsonArray);
		System.out.println(jsonArray.get(1));
		*/

        return jsonInString;
    }

}

