class Radiator {
    constructor(thermalCapacity, color, manufacturer, wheelBase) {
        this.thermalCapacityInVats = thermalCapacity;
        this.color = color;
        this.manufacturer = manufacturer;

this.wheelBaseInMillimeters = wheelBase;
    }
    get thermalCapacity() {
        return this.thermalCapacityInVats
    }

    get wheelBase() {
        return this.wheelBaseInMillimeters
    }
}

class Tracker {
    constructor(algoName) {
        this.algorithmName = algoName;
        this.comparisonCount = 0;
        this.swapCount = 0;
        this.executionTime = 0;
    }

    swapparison() {
        this.comparisonCount++;
    }

    swap() {
        this.swapCount++;
    }

    getComparison() {
        return this.comparisonCount;
    }

    getSwap() {
        return this.swapCount;
    }

    setExecutionTime(begin) {
        const execTime = process.hrtime(begin);
        this.executionTime = execTime[1] / 1000000000;
    }

    get trackerInfo() {
        return `
            Comparisons: ${getComparison()}
            Swaps: ${getSwap()}
        `
    }

}
const mergeSort = array => {
    const mergeTracker = new Tracker('merge sort');
    const begin = process.hrtime();

    function sort(arr) {
        if (arr.length <= 1) return arr;

        const middle = Math.floor(arr.length / 2);
        const leftSide = arr.slice(0, middle);
        const rightSide = arr.slice(middle);

        return merge(sort(leftSide), sort(rightSide));
    }

    function merge(left, right) {

        let resultArr = [];
        let leftIndex = 0;
        let rightIndex = 0;


        while (leftIndex < left.length && rightIndex < right.length)
        {
            mergeTracker.comparison();
            if (left[leftIndex].wheelBase < right[rightIndex].wheelBase) {
                resultArr.push(left[leftIndex]);
                leftIndex++;
                mergeTracker.swap();
            } else {
                resultArr.push(right[rightIndex]);
                rightIndex++;
                mergeTracker.swap();
            }
        }

        resultArr = resultArr
            .concat(left.slice(leftIndex))
            .concat(right.slice(rightIndex));

        return resultArr;
    }

    mergeTracker.setExecutionTime(begin);

    const sortedArray = sort(array);

    return {
        mergeTracker,
        mergeArray: sortedArray,
    };
};
const selectionSort = arr => {
    const selectTracker = new Tracker('Selection sort');
    const begin = process.hrtime();

    for (let i = 0; i < arr.length - 1; i++) {
        let maxIndex = i;
        let changed = false;

        for (let j = i + 1; j < arr.length; j++) {
            selectTracker.comparison();
            if (arr[maxIndex].thermalCapacity < arr[j].thermalCapacity) {
                maxIndex = j;
                changed = true;
            }
        }

        if (changed) {
            let temporary = arr[i];

            arr[i] = arr[maxIndex];
            selectTracker.swap();

            arr[maxIndex] = temporary;
            selectTracker.swap();
        };
    }

    selectTracker.setExecutionTime(begin);

    return {
        selectTracker,
        selectionArray: arr,
    };
};
const radiator = [
    new Radiator(900, "yellow", "Djoul", 2.5),
    new Radiator(1000, "Brown", "Kermi", 3),
    new Radiator(1100, "Black", "DaVinchi", 3.5),
    new Radiator(1200, "White", "Paskal", 4),
    new Radiator(1500, "Red", "Djoul", 4.5),
    new Radiator(1400, "Gray", "Kermi", 5)
];
console.log(mergeSort(radiator));
console.log(selectionSort(radiator));