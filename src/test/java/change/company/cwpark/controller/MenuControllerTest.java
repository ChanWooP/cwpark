package change.company.cwpark.controller;

import change.company.cwpark.data.dto.MenuDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.web.servlet.ResultMatcher;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(MenuController.class)
public class MenuControllerTest {

    private final MockMvc mvc;

    @Autowired
    public MenuControllerTest (MockMvc mvc) {
        this.mvc = mvc;
    }

    /*

    @Test
    @DisplayName("메뉴 전체 가져오기")
    void getAllMenu() throws Exception{

        // given
        List<MenuDto> menu = new ArrayList<>();
        menu.add(new MenuDto(null, 0, "ROOT"));
        menu.add(new MenuDto(1L, 1, "대1"));
        menu.add(new MenuDto(2L, 2, "중1"));

        // when

        //then
        mvc.perform(get("/menu/all")
                        .sessionAttr("menu", menu))
                .andExpect(status().isOk()) // 200 상태 확인
                .andExpect(MockMvcResultMatchers.model().attributeExists("menu")); // 해당 프로퍼티가 존재하는지 확인
    }

     */
}
