class ProfileService {
    getRoleView(roleArray) {
        if (roleArray.includes("ROLE_MODERATOR"))
            return "Администратор";
        if (roleArray.includes("ROLE_COACH"))
            return "Тренер";
        if (roleArray.includes("ROLE_USER"))
            return "Пользователь";
    }
    declinationRuble(num) {
        const ruble = ['рубль', 'рубля', 'рублей'];
        const number = Math.abs(num) % 100;
        const num1 = number % 10;

        return (num + " " + ruble[(number > 10 && number < 20) ? 2 : (num1 > 1 && num1 < 5) ? 1 : (num1 === 1) ? 0 : 2]);
    }

}

export default new ProfileService();