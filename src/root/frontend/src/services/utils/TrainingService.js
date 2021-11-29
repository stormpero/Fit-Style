class TrainingService {
    getStatusName(statusType) {
        switch (statusType) {
            case "LOGGED":
                return "Свободна";
            case "ACTIVE":
                return "Занята";
            case "COMPLETED":
                return "Закончена"
        }
    }
}

export default new TrainingService();