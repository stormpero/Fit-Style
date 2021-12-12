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

    getCutFio(fitUser) {
        return `${fitUser?.surname} ${fitUser?.name.slice(0, 1)}. ${fitUser?.patronymic.slice(0, 1)}.`
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
                styles.style.color = "white"
                styles.style.backgroundColor = "#1da614"
                styles.style.borderColor = "#13730e"
                break;
            case "ACTIVE":
                styles.style.color = "white"
                styles.style.backgroundColor = "#4e54c8"
                styles.style.borderColor = "#8f94fb"
                break;
            case "COMPLETED":
                styles.style.color = "white"
                styles.style.backgroundColor = "#e62222"
                styles.style.borderColor = "#e62222"
                break;
            case "ENDED":
                styles.style.backgroundColor = "#8fcc8d"
                styles.style.borderColor = "#78c774"
                break;
            default:
                console.error("Error, unknown training status");
        }
        return styles;
    }

    getTrainingsListFromDate(startDate, endDate) {
        let trainingsDates = []
        let dateNoTime = new Date(startDate.getFullYear(), startDate.getMonth(), startDate.getDate());
        for (let i = startDate.getHours(); i < endDate.getHours(); i++) {
            trainingsDates.push(new Date(dateNoTime.setHours(i)));
        }
        return trainingsDates;
    }
}

export default new ScheduleService();