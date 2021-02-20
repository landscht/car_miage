package fr.car.tp2.converter;

public interface GenericConverter<Dto,Entity> {
    Dto entityToDto(Entity entity);
    Entity dtoToEntity(Dto dto);
}
