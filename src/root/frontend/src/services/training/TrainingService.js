class TrainingService {
    getStatusName(statusType) {
        switch (statusType) {
            case "LOGGED":
                return "Свободна";
            case "ACTIVE":
                return "Занята";
            case "COMPLETED":
                return "Закончена";
            default:
                return "Ошибка";
        }
    }
}

export default new TrainingService();