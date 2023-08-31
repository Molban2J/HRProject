

// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Area Chart Example

// $(document).ready(function(){
//   getGraph();
// });
//
// function getGraph() {
//   let dateList = [];

//   $.ajax({
//     url: "/getLineChart",
//     type: "get",
//     success: function (responseData) {
//       var chartData = responseData.data; // 수정: 데이터 가져오기
//       var chartDate = responseData.chartDate; // 수정: 날짜 목록 가져오기
//
//       var datasets = [];
//
//       for (let i = 0; i < chartData.length; i++) {
//         var dataset = {
//           label: chartData[i].label,
//           data: chartData[i].data, // 수정: 중요도 데이터
//           backgroundColor: "rgba(2,117,216,0.2)",
//           borderColor: "rgba(2,117,216,1)",
//           pointRadius: 5,
//           pointBackgroundColor: "rgba(2,117,216,1)",
//           pointBorderColor: "rgba(255,255,255,0.8)",
//           pointHoverRadius: 5,
//           pointHoverBackgroundColor: "rgba(2,117,216,1)",
//           pointHitRadius: 50,
//           pointBorderWidth: 2,
//         };
//
//         datasets.push(dataset); // 데이터셋 배열에 추가
//       }
//
//       var ctx = document.getElementById("myAreaChart");
//       var myLineChart = new Chart(ctx, {
//         type: 'line',
//         data: {
//           labels: chartDate, // 수정: 날짜 목록
//           datasets: [datasets],
//         },
//         options: {
//           scales: {
//             x: {
//               type: 'time', // x축 타입을 시간으로 설정
//               time: {
//                 unit: 'day', // 날짜 단위로 표시
//                 displayFormats: {
//                   day: 'YYYY-MM-DD' // 날짜 포맷 설정
//                 }
//               },
//               grid: {
//                 display: false
//               },
//               ticks: {
//                 maxTicksLimit: 7
//               }
//             },
//             y: {
//               beginAtZero: true,
//               max: 40000,
//               maxTicksLimit: 5,
//               grid: {
//                 color: "rgba(0, 0, 0, .125)",
//               }
//             },
//           },
//           plugins: {
//             legend: {
//               display: false
//             }
//           }
//         }
//       });
//     }
//   });
// }

  var ctx = document.getElementById("myAreaChart");
  var myLineChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: ["Mar 1", "Mar 2", "Mar 3", "Mar 4", "Mar 5", "Mar 6", "Mar 7", "Mar 8", "Mar 9", "Mar 10", "Mar 11", "Mar 12", "Mar 13"],
      datasets: [{
        label: "Sessions",
        lineTension: 0.3,
        backgroundColor: "rgba(2,117,216,0.2)",
        borderColor: "rgba(2,117,216,1)",
        pointRadius: 5,
        pointBackgroundColor: "rgba(2,117,216,1)",
        pointBorderColor: "rgba(255,255,255,0.8)",
        pointHoverRadius: 5,
        pointHoverBackgroundColor: "rgba(2,117,216,1)",
        pointHitRadius: 50,
        pointBorderWidth: 2,
        data: [10000, 30162, 26263, 18394, 18287, 28682, 31274, 33259, 25849, 24159, 32651, 31984, 38451],
      }],
    },
    options: {
      scales: {
        xAxes: [{
          time: {
            unit: 'date'
          },
          gridLines: {
            display: false
          },
          ticks: {
            maxTicksLimit: 7
          }
        }],
        yAxes: [{
          ticks: {
            min: 0,
            max: 40000,
            maxTicksLimit: 5
          },
          gridLines: {
            color: "rgba(0, 0, 0, .125)",
          }
        }],
      },
      legend: {
        display: false
      }
    }
  });

