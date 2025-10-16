package school.sptech;

import school.sptech.Jobs.ParametrosAPI;
import school.sptech.Jobs.ProcessoGrau1API;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello, World!");
        ParametrosAPI.setTOKEN("hdVPC0gzW8u6f9cb6cvCC75d-G6Q1brCLjy_NWJG");
        ParametrosAPI.setParametroOab("509556");
        ProcessoGrau1API.getApiParams();

    }
}