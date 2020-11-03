/*
 * Copyright (c)
 * Musat Bianca, 323CA
*/

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import game.Input;
import game.Simulator;

public final class Main {

    private Main() { }

    public static void main(String[] args) throws JsonParseException,
                                    JsonMappingException, IOException {
        String path = args[0];  // get the input file name
        File file = new File(path);  // create the input file
        ObjectMapper objectMapper = new ObjectMapper();
        Input input = objectMapper.readValue(file, Input.class);  // read
                                                                // the input
        Simulator sim = Simulator.getInstance(); // get the Simulation instance
        sim.simulation(input);  // call the simulation method
    }

}
