const colors = [
    'rgb(255, 137, 54)',
    'rgb(62, 237, 231)',
    'rgb(23, 124, 176)',
    'rgb(240, 194, 57)',
    'rgb(128, 128, 128)',
    'rgb(240, 194, 57)',
    'rgb(188, 230, 114)',
    'rgb(23, 124, 176)',
    'rgb(128, 128, 128)',
    'rgb(255, 137, 54)'
  ];

  export function randomColor(){
    var index = Math.floor(Math.random()*10);
    return colors[index];
  }