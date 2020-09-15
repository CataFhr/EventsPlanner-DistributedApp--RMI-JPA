package server.convert;

import lib.dto.UserDto;
import server.model.User;

public final class UserConvertor {

    private UserConvertor() {
    }

    public static User convert(UserDto userDto) {
        var user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public static UserDto convert(User user) {
        var userDto = new UserDto(user.getUsername(), user.getPassword());
        userDto.setId(user.getId());
        return userDto;
    }

}
