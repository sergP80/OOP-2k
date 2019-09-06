package ua.edu.chmnu.fks.oop.patterns.facade;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private OrderDao orderDao;

    @Override
    public void addOrder(User user, Order order) {
        List<Order> orderList = orderDao.all()
                .stream()
                .filter(o -> o.getUser().equals(user))
                .collect(Collectors.toList());

        orderList.add(order);
        orderDao.save(orderList);
        userDao.updateOrderCount(user, orderList.size());
    }
}
