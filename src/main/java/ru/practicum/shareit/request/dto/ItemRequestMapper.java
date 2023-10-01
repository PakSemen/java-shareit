package ru.practicum.shareit.request.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ItemRequestMapper {

    public static ItemRequest itemRequestShortDtoToItemRequest(ItemRequestShortDto itemRequestShortDto, User user) {
        return ItemRequest.builder()
                .description(itemRequestShortDto.getDescription())
                .requester(user)
                .created(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

    public static ItemRequestDto itemRequestToRequestDto(ItemRequest itemRequest) {
        return ItemRequestDto.builder()
                .id(itemRequest.getId())
                .description(itemRequest.getDescription())
                .created(itemRequest.getCreated())
                .requesterId(itemRequest.getRequester().getId() != null ? itemRequest.getRequester().getId() : null)
                .build();
    }

    public static ItemRequestDto itemRequestToRequestWithItems(ItemRequest itemRequest, List<ItemDto> items) {
        return ItemRequestDto.builder()
                .id(itemRequest.getId())
                .description(itemRequest.getDescription())
                .created(itemRequest.getCreated())
                .items(items.isEmpty() ? new ArrayList<>() : items)
                .build();
    }

    public static ItemRequestShortDto itemRequestToItemRequestShortDto(ItemRequest itemRequest) {
        return ItemRequestShortDto.builder()
                .id(itemRequest.getId())
                .description(itemRequest.getDescription())
                .build();
    }

    public static ItemRequestDto toItemRequestDto(ItemRequest itemRequest, List<ItemDto> items) {
        return new ItemRequestDto(itemRequest.getId(), itemRequest.getDescription(),
                itemRequest.getRequester().getId(),
                itemRequest.getCreated(), items);
    }
}

