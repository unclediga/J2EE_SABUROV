package ru.unclediga.saburov.student.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.unclediga.saburov.student.dao.StudentOrderRepository;
import ru.unclediga.saburov.student.domain.StudentOrder;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentOrderService {
    @Autowired
    private StudentOrderRepository dao;

    @Transactional
    public List<StudentOrder> test() {
        return dao.findAll();

    }
}
