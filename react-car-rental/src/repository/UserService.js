import { userService } from '../custom-axios/axios';

const UserService = {
    fetchUsers: () => {
        return userService.get('/');
    },
    fetchUserById: (id) => {
        return userService.get(`/${id}`);
    },
    register: (registerDTO) => {
        return userService.post('/register', registerDTO);
    },
    login: (loginDTO) => {
        return userService.post("/login", loginDTO);
    },
    getUser: () => {
        return userService.get("/get-user", {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('JWT')}`
            }
        });
    }


};

export default UserService;
