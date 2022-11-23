const animals = ["Elephant","Dog","Cat","Horse","Ape"];

animals.sort();
console.log("Sorted Array : ");
console.log(animals);

animals.reverse();
console.log("Reversed Array : ")
console.log(animals);

const points = [40,100,34,56,2,67,23];

console.log("Array sort ascending.");
points.sort(function(a,b){return a - b});
console.log(points);

console.log("Array sort descending.");
points.sort(function(a,b){return b - a});
console.log(points);