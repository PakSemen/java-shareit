package ru.practicum.shareit.request.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

import java.io.PushbackReader;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ItemRequestMapperTest {
    private Item item;
    private User user;
    private ItemRequest itemRequest;

    @BeforeEach
    void beforeEach() {
        user = new User(1L, "User_name", "user@test.testz");
        item = new Item(1L, "Item_name", "Item_Description", true, user,
                null);
        itemRequest = new ItemRequest(1L, "Description", user,
                Timestamp.valueOf(LocalDateTime.now()));
    }

    @Test
    void itemRequestToRequestDtoTest() {
        ItemRequestDto itemRequestDto = ItemRequestMapper.itemRequestToRequestDto(itemRequest);

        assertThat(itemRequestDto.getId()).isEqualTo(itemRequest.getId());
        assertThat(itemRequestDto.getDescription()).isEqualTo(itemRequest.getDescription());
        assertThat(itemRequestDto.getRequesterId()).isEqualTo(itemRequest.getRequester().getId());
        assertThat(itemRequestDto.getCreated()).isEqualTo(itemRequest.getCreated());
    }

    @Test
    void itemRequestShortDtoToItemRequestTest() {
        ItemRequestShortDto itemRequestShortDto = new ItemRequestShortDto(itemRequest.getId(), itemRequest.getDescription());
        User user = new User();
        ItemRequest itemRequest = ItemRequestMapper.itemRequestShortDtoToItemRequest(itemRequestShortDto, user);

        assertThat(itemRequest.getDescription()).isEqualTo(itemRequestShortDto.getDescription());
    }

    @Test
    void itemRequestToRequestWithItemsTest() {
        List<ItemDto> items = new ArrayList<>();
        ItemRequestDto itemRequestDto = ItemRequestMapper.itemRequestToRequestWithItems(itemRequest,items);

        assertThat(itemRequestDto.getId()).isEqualTo(itemRequest.getId());
        assertThat(itemRequestDto.getDescription()).isEqualTo(itemRequest.getDescription());
        assertThat(itemRequestDto.getCreated()).isEqualTo(itemRequest.getCreated());
    }

}