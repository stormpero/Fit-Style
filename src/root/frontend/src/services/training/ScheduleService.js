class ScheduleService {
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

    getEventStatusColor = (status) => {
        let styles = {
            style: {
                backgroundColor: "",
                borderColor: "",
                color: "black"
            }
        }
        switch (status) {
            case "LOGGED":
                styles.style.backgroundColor = "#27FB6B"
                styles.style.borderColor = "#27FB6B"
                break;
            case "ACTIVE":
                styles.style.backgroundColor = "#00E8FC"
                styles.style.borderColor = "#00E8FC"
                break;
            case "COMPLETED":
                styles.style.backgroundColor = "#e62222"
                styles.style.borderColor = "#e62222"
                break;
            case "ENDED":
                styles.style.backgroundColor = "#93ff8e"
                styles.style.borderColor = "#0bff00"
                break;
            default:
                console.error("Error, unknown training status");
        }
        return styles;
    }
}

export default new ScheduleService();