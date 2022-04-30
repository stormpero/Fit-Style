let storage = null;

const setItem = (name, value) => {
  if (typeof storage.setItem !== "function") {
    console.error("Storage should implement setItem method");
  }
  storage.setItem(name, value);
}

const getItem = (name) => {
  if (typeof storage.getItem !== "function") {
    console.error("Storage should implement getItem method");
  }
  return storage.getItem(name);
}

const removeItem = (name) => {
  if (typeof storage.removeItem !== "function") {
    console.error("Storage should implement removeItem method");
  }
  storage.removeItem(name);
}

export {
  setItem,
  getItem,
  removeItem
}

export const setStorage = (instance) => {
  storage = instance;
};