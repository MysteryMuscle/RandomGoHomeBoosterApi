package org.mysterymuscle.randomgohomebooster.controller;

import lombok.RequiredArgsConstructor;
import org.mysterymuscle.randomgohomebooster.annotation.CurrentUser;
import org.mysterymuscle.randomgohomebooster.domain.Deck;
import org.mysterymuscle.randomgohomebooster.domain.Member;
import org.mysterymuscle.randomgohomebooster.dto.DeckDto;
import org.mysterymuscle.randomgohomebooster.service.DeckService;
import org.mysterymuscle.randomgohomebooster.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/deck")
@RequiredArgsConstructor
public class DeckAdminController {

    private final DeckService deckService;
    private final MemberService memberService;


}
