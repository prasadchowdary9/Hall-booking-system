package com.example.demo.mapper;

import com.example.demo.dto.SlotDTO;
import com.example.demo.model.Slot;

public class SlotMapper {

    public static Slot dtoToEntity(SlotDTO dto) {
        Slot slot = new Slot();
        slot.setId(dto.getId());
        slot.setTime(dto.getTime());
        slot.setAvailable(dto.isAvailable());
        return slot;
    }
}
