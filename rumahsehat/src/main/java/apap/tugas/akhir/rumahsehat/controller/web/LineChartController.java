package apap.tugas.akhir.rumahsehat.controller.web;


import apap.tugas.akhir.rumahsehat.service.TagihanService;
import apap.tugas.akhir.rumahsehat.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.*;

import java.util.Optional;

@Controller
public class LineChartController {
    @Autowired
    TagihanService tagihanService;

    @Autowired
    UserService userService;


    @GetMapping("/chart")
    public String defaultLineChart(Model model, Principal principal) {
        if (userService.isAdmin(principal)) {
            Map<String, Long> data = new LinkedHashMap<>();
            List<String> months = new ArrayList<String>(Arrays.asList("JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"));

            for (String month : months) {
                Long pendapatan = tagihanService.getTotalPendapatan(month);
                data.put(month, pendapatan);
            }
            model.addAttribute("data", data);
            return "dashboard/chart/linechart-default";
        }
        else {
            return "error/404";
        }

    }
}
