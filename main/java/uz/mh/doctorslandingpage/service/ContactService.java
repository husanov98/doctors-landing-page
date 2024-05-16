package uz.mh.doctorslandingpage.service;

import org.springframework.stereotype.Service;
import uz.mh.doctorslandingpage.dto.ContactDto;
import uz.mh.doctorslandingpage.mapper.ContactMapper;
import uz.mh.doctorslandingpage.model.Contact;
import uz.mh.doctorslandingpage.model.Doctor;
import uz.mh.doctorslandingpage.repository.ContactRepository;
import uz.mh.doctorslandingpage.repository.DoctorRepository;
import uz.mh.doctorslandingpage.response.ApiResponse;

import java.util.Optional;

@Service
public class ContactService {
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private final DoctorRepository doctorRepository;

    public ContactService(ContactRepository contactRepository, ContactMapper contactMapper, DoctorRepository doctorRepository) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
        this.doctorRepository = doctorRepository;
    }

    public ApiResponse<String> addContact(ContactDto contactDto){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Contact contact = contactMapper.mapToContact(contactDto);
        Optional<Doctor> optionalDoctor = doctorRepository.findById(contactDto.getDoctorId());
        Optional<Contact> optionalContact = contactRepository.findByPhoneNumber(contactDto.getPhoneNumber());
        if (optionalDoctor.isPresent()){
            if (optionalContact.isEmpty()){
                contact.setDoctor(optionalDoctor.get());
                contactRepository.save(contact);
            }else {
                optionalContact.get().setDoctor(optionalDoctor.get());
            }
            apiResponse.setBody("Contact successfully added");
            apiResponse.setStatus(201);
        }else {
            apiResponse.setBody("This user is not registered");
            apiResponse.setSuccess(false);
            apiResponse.setStatus(403);
        }
        return apiResponse;
    }
}
