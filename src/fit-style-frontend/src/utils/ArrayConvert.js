class ArrayConvert {
    sliceArray(array, size) {
        return array.reduce((prev,curr)=>{
            if(prev[prev.length-1].length === size) prev.push([]);
            prev[prev.length-1].push(curr);
            return prev;
        }, [[]]);
    }
}

export default new ArrayConvert();
