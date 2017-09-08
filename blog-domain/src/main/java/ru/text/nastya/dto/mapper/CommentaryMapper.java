package ru.text.nastya.dto.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ru.text.nastya.domain.entities.Commentary;
import ru.text.nastya.dto.CommentaryDto;

@Mapper(withIoC = IoC.SPRING,
        withIoCServiceName = "commentaryMapper",
        withIgnoreMissing = IgnoreMissing.ALL,
        withIgnoreNullValue = true)
public interface CommentaryMapper extends EntityMapper<Commentary, CommentaryDto> {
}
