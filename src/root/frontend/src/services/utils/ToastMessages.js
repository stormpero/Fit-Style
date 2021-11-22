import cogoToast from 'cogo-toast';

class ToastMessages {
    error(msg, position) {
        cogoToast.error(msg, { position: position });
    }
    success(msg, position) {
        cogoToast.success(msg, { position: position });
    }

    loading(msg) {
        return cogoToast.loading(msg)
    }
}

export default new ToastMessages()