class TrainingService {
    concatTrainings(data) {
        let {personalTrainings, groupTrainings} = data;
        personalTrainings.forEach(value => {
            value.startDate = new Date(value.startDate);
            value.endDate = new Date(value.endDate);
            value.isPersonal = true;
            return value;
        })

        groupTrainings.forEach(value => {
            value.startDate = new Date(value.startDate);
            value.endDate = new Date(value.endDate);
            return value;
        })
        return  personalTrainings.concat(groupTrainings);
    }
}

export default new TrainingService();