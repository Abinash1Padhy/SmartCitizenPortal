package com.user108.UserService.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.user108.UserService.dto.FilterUserDto;
import com.user108.UserService.dto.UserDto;
import com.user108.UserService.entity.Address;
import com.user108.UserService.entity.User;
import com.user108.UserService.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserServiceImpl implements UserService{

	private final static Logger logger = Logger.getLogger("UserServiceImpl");

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@PostConstruct
	private void createUsers(){
		LocalDateTime dateTime = LocalDateTime.now();
		User u1 = new User("Vishnu Prasad","vishnu.prasad@gmail.com","99995567700","ADMIN","vishnu@108",dateTime,new Address("4th Gali","Savarkar Nagar","Surat","Gujarat","Bharat")),
				u2 = new User("Swamy Vivekanand","swami.vivekananda@gmail.com","99995567711","ADMIN","vivekananda@108",dateTime,new Address("2rd Lane Cross", "GajapatiNagar","Bhubaneswar","Odisha","Bharat")),
				u3 = new User("Sai Krishna","sai.krishna@gmail.com","99995567701","CITIZEN","Krushna@1089",dateTime,new Address("B11/9 CMPDI Quarters", "Chandrasekharpur","BBSR","Odisha","Bharat"));
		
		List<User> users = new ArrayList<>();
		users.add(u1);users.add(u2);users.add(u3);
		
		userRepo.saveAll(users);
	}
	
	@Override
	public List<UserDto> getUsers(FilterUserDto filterDto){

		// Default pagination if null
	    int pageNo = (filterDto.getPageNumber() != null) ? filterDto.getPageNumber() : 0;
	    int pageSize = (filterDto.getPageSize() != null) ? filterDto.getPageSize() : 10;

	    List<UserDto> usersDto = new ArrayList<UserDto>();
		Pageable paging  = PageRequest.of(pageNo, pageSize);

		List<Criteria> filters = new ArrayList();
		
		if(filterDto.getCitizenIds()!=null && !filterDto.getCitizenIds().isEmpty())
			filters.add(Criteria.where("citizenId").in(filterDto.getCitizenIds()));
		
		if(filterDto.getEmails()!=null && !filterDto.getEmails().isEmpty())
			filters.add(Criteria.where("email").in(filterDto.getEmails()));
		
		if(filterDto.getPhone()!=null && !filterDto.getPhone().isEmpty())
			filters.add(Criteria.where("phone").in(filterDto.getPhone()));
		
		if(filterDto.getFromDate()!=null || filterDto.getToDate()!=null)
		{
			Criteria dateCriteria = Criteria.where("created");
			if(filterDto.getFromDate()!=null && filterDto.getToDate()!=null)
				dateCriteria.gte(filterDto.getFromDate()).lte(filterDto.getToDate());
			else if(filterDto.getFromDate()!=null)
				dateCriteria.gte(filterDto.getFromDate());
			else
				dateCriteria.lte(filterDto.getToDate());
			
			filters.add(dateCriteria);
		}
		
		Criteria allFilters = (!filters.isEmpty())?new Criteria().orOperator(filters.toArray(new Criteria[0])) : new Criteria();
		Query query = new Query(allFilters).with(paging);
		List<User> users = mongoTemplate.find(query, User.class);
		List<UserDto> userDtos=new ArrayList<>();
		users.forEach(x-> userDtos.add(new UserDto(x)));
		
		return usersDto;
		
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		
		logger.info("Logger:saveUser"+userDto);
		
		User user = new User(userDto);
		User newUser = userRepo.save(user);
		UserDto dto = new UserDto(newUser);

		return dto;
	}

	@Override
	public UserDto findById(String citizenId) {
		
		Optional<User> key = userRepo.findById(citizenId);
		if(key.isPresent())
			return new UserDto(key.get());
		return null;
	}

	@Override
	public UserDto deleteById(String citizenId) {
		
		if(citizenId==null)
			return null;
		Optional<User> u = userRepo.findById(citizenId);
		
		if(u.isPresent())
		{	
			userRepo.deleteById(citizenId);
			return new UserDto(u.get());
		}
		else 
			return null;
	}
}
