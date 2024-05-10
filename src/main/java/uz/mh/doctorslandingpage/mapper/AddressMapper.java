package uz.mh.doctorslandingpage.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.mh.doctorslandingpage.dto.AddressDto;
import uz.mh.doctorslandingpage.model.Address;

@Component
public class AddressMapper {
    private final ModelMapper modelMapper = new ModelMapper();
    public Address mapToAddress(AddressDto addressDto){return modelMapper.map(addressDto,Address.class);}
    public AddressDto mapToAddressDto(Address address){return modelMapper.map(address, AddressDto.class);}
}
