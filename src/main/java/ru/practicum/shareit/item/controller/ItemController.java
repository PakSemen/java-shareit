package ru.practicum.shareit.item.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemService service;

    @PostMapping
    public ItemDto createItem(@Valid @RequestBody ItemDto itemDto,
                              @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("The item with id = {} has been created", itemDto.getId());
        return service.createItem(itemDto, userId);
    }

    @GetMapping("/{itemId}")
    public ItemDto getItemById(@PathVariable Long itemId,
                               @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Get item with id = {} belong user with id = {}", itemId, userId);
        return service.getItemById(itemId, userId);
    }

    @GetMapping
    public List<ItemDto> getAllItems(@RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Get all items of user with id = {}", userId);
        return service.getAllItems(userId);
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItemById(@RequestBody ItemDto itemDto,
                                  @PathVariable Long itemId,
                                  @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("The item with id = {} of user with id = {} has been updated", itemId, userId);
        return service.updateItemById(itemDto, itemId, userId);
    }

    @GetMapping("/search")
    public List<ItemDto> searchItemByText(@RequestParam String text,
                                          @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Search item of user with id = {} by text = \" {} \"", userId, text);
        return service.searchItemByText(text.toLowerCase(), userId);
    }

    @DeleteMapping("/{itemId}")
    public ItemDto deleteItemById(@PathVariable Long itemId,
                                  @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("delete item with id = {} from user with id = {}", itemId, userId);
        return service.removeItemById(itemId, userId);
    }
}