package uz.mh.doctorslandingpage.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.mh.doctorslandingpage.dto.ContactDto;
import uz.mh.doctorslandingpage.model.Contact;

@Component
public class ContactMapper {
    private final ModelMapper modelMapper = new ModelMapper();
    public Contact mapToContact(ContactDto contactDto){return modelMapper.map(contactDto,Contact.class);}
    public ContactDto mapToContactDto(Contact contact){return modelMapper.map(contact, ContactDto.class);}
}
