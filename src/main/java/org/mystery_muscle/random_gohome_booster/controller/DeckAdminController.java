package org.mystery_muscle.random_gohome_booster.controller;

import lombok.RequiredArgsConstructor;
import org.mystery_muscle.random_gohome_booster.annotation.CurrentUser;
import org.mystery_muscle.random_gohome_booster.domain.Deck;
import org.mystery_muscle.random_gohome_booster.domain.Member;
import org.mystery_muscle.random_gohome_booster.dto.DeckDto;
import org.mystery_muscle.random_gohome_booster.service.DeckService;
import org.mystery_muscle.random_gohome_booster.service.MemberService;
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

    @RequestMapping(value = "/list")
    public String deckListGet(@PageableDefault Pageable pageable, Model model) {
        Page<Deck> decks = deckService.getAllDecksPages(pageable.getPageNumber(), pageable.getPageSize());
        model.addAttribute("page", decks);
        int totalPages = decks.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "/admin/deck/list";

    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String deckFormGet(@ModelAttribute DeckDto deckDto, Model model) {
        model.addAttribute("deckDto", deckDto);
        return "/admin/deck/form";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String deckFormPost(@CurrentUser Member currentUser, @ModelAttribute DeckDto deckDto) {
        Member owner = memberService.getMember(currentUser.getLoginId());
        Deck deck = Deck.createDeck(owner, deckDto.getName(), deckDto.getDescription());
        deck = deckService.insertDeck(deck);
        return "redirect:/admin/deck/list";
    }

}
