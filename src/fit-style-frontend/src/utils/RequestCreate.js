class RequestCreate {
    createRequestData(data, img) {
        const blob = new Blob([JSON.stringify(data)], {
            type: 'application/json',
        });
        const formData = new FormData();
        formData.append('request', blob);
        formData.append('image', img);

        return formData;
    }
}

export default new RequestCreate();
