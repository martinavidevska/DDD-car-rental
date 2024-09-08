import { userService } from '../custom-axios/axios';

const UserService = {
    fetchUsers: () => {
        return userService.get('/');
    },
    fetchUserById: (id) => {
        return userService.get(`/${id}`);
    },
    addUser: (user) => {
        return userService.post('/add', user);
    },
    login: (username, password) => {
        return userService.post('/login', username, password);
    }
};

export default UserService;
