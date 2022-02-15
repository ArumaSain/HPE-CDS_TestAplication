package com.hpecds.testaplication.Controller;

import com.hpecds.testaplication.Model.Kpis;
import com.hpecds.testaplication.Model.Metrics;
import com.hpecds.testaplication.Services.IMPL.TestServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;


@Controller
@RequestMapping("/MCP")
public class MCPController {

    @Autowired
    private TestServiceIMPL testServiceIMPL;


    @GetMapping ("/index")
    public String index () {
        return "index";
    }

    @PostMapping("/logs")
    public ModelAndView ReadJsonFile (@RequestAttribute("file") MultipartFile file) {
        ModelAndView mov = new ModelAndView("logs");
        String filename = file.getOriginalFilename();
        ArrayList<String> lines = testServiceIMPL.ReadFile(filename);

        Metrics metrics = testServiceIMPL.Metrics(lines);
        Kpis kpis = testServiceIMPL.Kpis(lines);

        mov.addObject("metrics", metrics);
        mov.addObject("kpis", kpis);

        return mov;
    }

    @PostMapping("/metrics")
    public String Metrics (@ModelAttribute("metrix") Metrics metrics) {
        System.out.println(metrics.toString());
        return "metrics";
    }

    @PostMapping("/kpis")
    public String Kpis (@ModelAttribute("kpis") Kpis kpis) {
        System.out.println(kpis.toString());
        return "kpis";
    }


}
