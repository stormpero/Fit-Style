class ProfileService {
    getRoleView(roleArray) {
        if (roleArray.includes("ROLE_MODERATOR"))
            return "Администратор";
        if (roleArray.includes("ROLE_COACH"))
            return "Тренер";
        if (roleArray.includes("ROLE_USER"))
            return "Пользователь";
    }
}

export default new ProfileService();