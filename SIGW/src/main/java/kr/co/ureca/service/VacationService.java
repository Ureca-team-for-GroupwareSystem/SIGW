package kr.co.ureca.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.ureca.entity.Employee;
import kr.co.ureca.entity.Vacation;
import kr.co.ureca.repository.VacationRepository;

@Service
public class VacationService {

    @Autowired
    private VacationRepository vacationRepository;

    public List<Vacation> getVacationsByEmployee(Employee employee) {
        return vacationRepository.findByEmployee(employee);
    }
}
