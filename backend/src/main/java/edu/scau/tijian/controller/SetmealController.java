package edu.scau.tijian.controller;

import edu.scau.tijian.pojo.Setmeal;
import edu.scau.tijian.service.SetmealService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/setmeal")
@RequiredArgsConstructor
public class SetmealController {
    private final SetmealService setmealService;

    @PostMapping("/getSetmealByType")
    public List<Setmeal> getSetmealByType(@RequestBody Setmeal setmeal) {
        Logger logger = LoggerFactory.getLogger(SetmealController.class);
        logger.info(setmeal.getType().toString());
        return setmealService.getSetmealByType(setmeal.getType());
    }
}
