const API_BASE_URL = 'http://localhost:8080/api'; 
const USE_MOCK_DATA = false; 

const VND_FORMATTER = new Intl.NumberFormat('vi-VN');
const CURRENT_YEAR = new Date().getFullYear();

async function fetchData(endpoint, options = {}) {
    const url = `${API_BASE_URL}${endpoint}`;
    try {
        const response = await fetch(url, options);
        if (response.status === 204) {
            return null;
        }
        if (!response.ok) {
            const errorText = await response.text(); 
            let errorMessage = `Lỗi HTTP: ${response.status}`;
            try {
                const errorBody = JSON.parse(errorText);
                errorMessage = errorBody.message || errorMessage;
            } catch {
                errorMessage = errorText || errorMessage;
            }
            throw new Error(errorMessage);
        }
        return await response.json();
    } catch (error) {
        console.error("Lỗi khi gọi API:", url, error);
        // Trả về lỗi để hàm gọi có thể hiển thị cho người dùng
        throw new Error(`Không thể kết nối hoặc lỗi dữ liệu: ${error.message}`); 
    }
}

// Hàm tiện ích: Mở/Đóng Modal
function openModal(modalId) {
    document.getElementById(modalId).style.display = 'block';
}

function closeModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
}

// Đóng modal khi click ra ngoài
window.onclick = function(event) {
    if (event.target.classList.contains('modal')) {
        event.target.style.display = 'none';
    }
}


// Hàm chuyển đổi nội dung và tải dữ liệu
function showContent(sectionName) {
    // Ẩn tất cả các sections
    document.querySelectorAll('.content-section').forEach(section => {
        section.classList.remove('active');
    });
    // Bỏ active khỏi tất cả các nút sidebar
    document.querySelectorAll('.sidebar-menu button').forEach(btn => {
        btn.classList.remove('active');
    });
    
    // Xử lý section và button ID
    let targetId = `section-${sectionName}`;
    let buttonId = `btn-${sectionName.toLowerCase().replace(/-/g, '-')}`;

    const targetSection = document.getElementById(targetId);
    const targetButton = document.getElementById(buttonId);
    
    if (targetSection) {
        targetSection.classList.add('active');
    }
    if (targetButton) {
        targetButton.classList.add('active');
    }

    // Tải dữ liệu tương ứng
    if (sectionName === 'room-management') {
        loadRoomDetailsTable();
    } else if (sectionName === 'customer-report') {
        loadCustomerReport();
    } else if (sectionName === 'revenue-report') {
        loadRoomFilterOptions().then(updatePeriodFilters); 
    } else if (sectionName === 'dashboard') {
        loadRoomsSummary();
    }
}

// =========================================================
// CHỨC NĂNG 0: DASHBOARD
// =========================================================

function updateDashboardSummary(rooms) {
    const occupiedCount = rooms.filter(r => r.status === 'OCCUPIED').length;
    const availableCount = rooms.filter(r => r.status === 'AVAILABLE').length;

    // Giả lập doanh thu hôm nay
    const totalDailyRevenue = 8500000; 
    const revenueFormatted = VND_FORMATTER.format(totalDailyRevenue);

    document.getElementById('occupied-count').textContent = occupiedCount;
    document.getElementById('available-count').textContent = availableCount;
    document.getElementById('daily-revenue').textContent = `${revenueFormatted} VND`;
}

async function loadRoomsSummary() {
    const roomListElement = document.getElementById('roomListSummary');
    roomListElement.innerHTML = '<p style="color: gray;">Đang tải trạng thái phòng...</p>';
    
    try {
        // Giả sử có API để lấy danh sách phòng
        const rooms = await fetchData('/rooms'); 
        
        updateDashboardSummary(rooms);

        if (!rooms || rooms.length === 0) {
            roomListElement.innerHTML = '<p style="color: #f44336;">Không có dữ liệu phòng.</p>';
            return;
        }

        roomListElement.innerHTML = ''; 
        rooms.forEach(room => { 
            const roomDiv = document.createElement('div');
            const statusClass = (room.status || 'default-status').toLowerCase(); 
            roomDiv.className = `room-status-item ${statusClass}`; 
            roomDiv.textContent = room.roomName; 
            // Kích hoạt chuyển sang tab quản lý phòng khi click
            roomDiv.onclick = () => showContent('room-management');
            roomListElement.appendChild(roomDiv);
        });

    } catch (error) {
        roomListElement.innerHTML = `<p style="color: #f44336;">Lỗi tải dữ liệu Dashboard: ${error.message}</p>`;
    }
}


// =========================================================
// CHỨC NĂNG 1: QUẢN LÝ PHÒNG (ROOM MANAGEMENT)
// =========================================================

async function loadRoomDetailsTable() {
    const tableContainer = document.getElementById('roomTableContainer');
    tableContainer.innerHTML = '<p style="color: gray;">Đang tải danh sách phòng chi tiết...</p>';

    try {
        const rooms = await fetchData('/rooms');
        
        if (!rooms || rooms.length === 0) {
            tableContainer.innerHTML = '<p style="color: #f44336;">Không tìm thấy phòng nào.</p>';
            return;
        }

        let html = '<table class="data-table">';
        html += '<thead><tr><th>ID</th><th>Tên Phòng</th><th>Loại Phòng</th><th>Giá/Giờ (VND)</th><th>Trạng Thái</th><th class="action-column">Thao Tác</th></tr></thead>';
        html += '<tbody>';
        
        rooms.forEach(room => {
            const statusClass = (room.status || 'default-status').toLowerCase();
            const priceFormatted = VND_FORMATTER.format(room.pricePerHour || 0);
            
            html += `<tr>
                <td>${room.id}</td>
                <td>${room.roomName}</td> 
                <td>${room.type || 'N/A'}</td>
                <td>${priceFormatted}</td>
                <td><span class="room-status-item ${statusClass}" style="padding: 5px 10px; display: block; width: fit-content; margin: auto;">${room.status}</span></td>
                <td class="action-column">
                    <button class="secondary-btn edit-btn" onclick="editRoom(${room.id})">Sửa</button>
                    <button class="secondary-btn delete-btn" onclick="deleteRoom(${room.id})">Xóa</button>
                </td>
            </tr>`;
        });
        
        html += '</tbody></table>';
        tableContainer.innerHTML = html;

    } catch (error) {
        tableContainer.innerHTML = `<p style="color: #f44336;">Lỗi tải danh sách phòng: ${error.message}</p>`;
    }
}

// --- Logic xử lý nút "Thêm Phòng Mới" (Create) ---
// Đây là hàm mà bạn yêu cầu
window.addNewRoom = function() { 
    // 1. Reset form trước khi mở
    document.getElementById('addRoomForm').reset();
    // 2. Mở modal tương ứng
    openModal('addRoomModal');
    // 3. Đặt focus vào trường tên phòng để người dùng nhập liệu ngay lập tức
    document.getElementById('add-room-name').focus();
}

document.getElementById('addRoomForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    
    const submitButton = e.target.querySelector('button[type="submit"]');
    submitButton.disabled = true;
    submitButton.textContent = 'Đang Tạo...';

    const newRoom = {
        roomName: document.getElementById('add-room-name').value,
        type: document.getElementById('add-room-type').value, 
        pricePerHour: parseFloat(document.getElementById('add-price-per-hour').value),
        status: document.getElementById('add-room-status').value, 
    };
    
    try {
        // Gọi API POST để tạo phòng mới
        const createdRoom = await fetchData('/rooms', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newRoom)
        });

        // Thay thế alert bằng một modal thông báo tùy chỉnh nếu bạn muốn
        alert(`Phòng "${createdRoom.roomName}" (ID: ${createdRoom.id}) đã được tạo thành công!`);
        closeModal('addRoomModal');
        loadRoomDetailsTable(); // Tải lại bảng quản lý phòng
        loadRoomsSummary(); // Cập nhật Dashboard

    } catch (error) {
        alert(`Lỗi khi tạo phòng: ${error.message}`);
    } finally {
        submitButton.disabled = false;
        submitButton.textContent = 'Tạo Phòng';
    }
});


// --- Chỉnh sửa phòng (Update) ---
window.editRoom = async function(roomId) {
    try {
        const roomToEdit = await fetchData(`/rooms/${roomId}`);

        document.getElementById('edit-room-id').value = roomToEdit.id;
        document.getElementById('edit-room-name').value = roomToEdit.roomName; 
        document.getElementById('edit-room-type').value = roomToEdit.type || 'THUONG'; 
        document.getElementById('edit-price-per-hour').value = roomToEdit.pricePerHour;
        document.getElementById('edit-room-status').value = roomToEdit.status;
        
        openModal('editRoomModal');

    } catch (error) {
        alert(`Lỗi khi lấy thông tin phòng: ${error.message}`);
    }
}

document.getElementById('editRoomForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    
    const submitButton = e.target.querySelector('button[type="submit"]');
    submitButton.disabled = true;
    submitButton.textContent = 'Đang Lưu...';

    const roomId = document.getElementById('edit-room-id').value;
    const updatedRoom = {
        roomName: document.getElementById('edit-room-name').value, 
        type: document.getElementById('edit-room-type').value,
        pricePerHour: parseFloat(document.getElementById('edit-price-per-hour').value),
        status: document.getElementById('edit-room-status').value,
    };
    
    try {
        await fetchData(`/rooms/${roomId}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(updatedRoom)
        });

        alert(`Phòng ID: ${roomId} đã được cập nhật thành công!`);
        closeModal('editRoomModal');
        loadRoomDetailsTable(); 
        loadRoomsSummary(); 

    } catch (error) {
        alert(`Lỗi khi cập nhật phòng: ${error.message}`);
    } finally {
        submitButton.disabled = false;
        submitButton.textContent = 'Lưu Thay Đổi';
    }
});


// --- Xóa phòng (Delete) ---
window.deleteRoom = async function(id) {
    // Lưu ý: Trong môi trường iFrame, không nên dùng confirm(). Nên thay bằng modal tùy chỉnh.
    if (confirm(`Bạn có chắc chắn muốn XÓA phòng ID: ${id}? Thao tác này không thể hoàn tác.`)) {
        try {
            await fetchData(`/rooms/${id}`, { method: 'DELETE' });
            alert(`Đã xóa phòng ID: ${id} thành công!`);
            loadRoomDetailsTable();
            loadRoomsSummary();
        } catch (error) {
            alert(`Lỗi khi xóa phòng: ${error.message}`);
        }
    }
}

// =========================================================
// CHỨC NĂNG 2: THỐNG KÊ KHÁCH HÀNG
// =========================================================

async function loadCustomerReport() {
    const contentElement = document.getElementById('customerReportContent');
    contentElement.innerHTML = '<p style="color: gray;">Đang tải dữ liệu top khách hàng...</p>';

    try {
        const topCustomers = await fetchData('/reports/top-customers?limit=10');
        
        if (!topCustomers || topCustomers.length === 0) {
            contentElement.innerHTML = '<p style="color: #ff9800;">Chưa có dữ liệu khách hàng.</p>';
            return;
        }
        
        let html = '<table class="data-table">';
        // Giả định API trả về customerName, phone và totalRevenue
        html += '<thead><tr><th>Khách Hàng</th><th>Số Điện Thoại</th><th>Doanh Thu (VND)</th></tr></thead>'; 
        html += '<tbody>';

        topCustomers.forEach(c => {
            const revenueFormatted = VND_FORMATTER.format(c.totalRevenue || 0); 
            html += `<tr>
                <td>${c.customerName || 'N/A'}</td> 
                <td>${c.phone || 'N/A'}</td> 
                <td>${revenueFormatted}</td>
            </tr>`;
        });

        html += '</tbody></table>';
        contentElement.innerHTML = html;
        
    } catch (error) {
        contentElement.innerHTML = `<p style="color: #f44336;">Lỗi tải báo cáo khách hàng: ${error.message}</p>`;
    }
}

// =========================================================
// CHỨC NĂNG 3: THỐNG KÊ DOANH THU THEO KỲ
// =========================================================

async function loadRoomFilterOptions() {
    const roomFilterElement = document.getElementById('roomFilter');
    
    try {
        const rooms = await fetchData('/rooms');

        const currentRoomId = roomFilterElement.value; 
        roomFilterElement.innerHTML = '<option value="all">Tất cả các phòng</option>';
        
        if (rooms) {
            rooms.forEach(room => {
                const option = document.createElement('option');
                option.value = room.id;
                option.textContent = room.roomName; // <-- SỬA LỖI: Sử dụng roomName
                roomFilterElement.appendChild(option);
            });
        }
        
        if (document.querySelector(`#roomFilter option[value="${currentRoomId}"]`)) {
            roomFilterElement.value = currentRoomId;
        }
    } catch (error) {
        console.error("Lỗi khi tải danh sách phòng cho filter:", error);
    }
}

function updatePeriodFilters() {
    const periodType = document.getElementById('periodType').value;
    const container = document.getElementById('dynamicPeriodFilters');
    container.innerHTML = '';
    
    let periodHtml = '';

    const yearOptions = `
        <option value="${CURRENT_YEAR}" selected>${CURRENT_YEAR}</option>
        <option value="${CURRENT_YEAR - 1}">${CURRENT_YEAR - 1}</option>
    `;

    if (periodType !== 'year') {
        periodHtml += `
            <label for="filterYear">Năm:</label>
            <select id="filterYear" onchange="loadRevenueReport()">
                ${yearOptions}
            </select>
        `;
    }

    if (periodType === 'month') {
        const currentMonth = new Date().getMonth() + 1;
        periodHtml += `
            <label for="filterMonth">Tháng:</label>
            <select id="filterMonth" onchange="loadRevenueReport()">
                ${Array.from({ length: 12 }, (_, i) => 
                    `<option value="${i + 1}" ${i + 1 === currentMonth ? 'selected' : ''}>Tháng ${i + 1}</option>`
                ).join('')}
            </select>
        `;
        
    } else if (periodType === 'quarter') {
        const currentQuarter = Math.ceil((new Date().getMonth() + 1) / 3);
        periodHtml += `
            <label for="filterQuarter">Quý:</label>
            <select id="filterQuarter" onchange="loadRevenueReport()">
                <option value="1" ${currentQuarter === 1 ? 'selected' : ''}>Quý 1</option>
                <option value="2" ${currentQuarter === 2 ? 'selected' : ''}>Quý 2</option>
                <option value="3" ${currentQuarter === 3 ? 'selected' : ''}>Quý 3</option>
                <option value="4" ${currentQuarter === 4 ? 'selected' : ''}>Quý 4</option>
            </select>
        `;
        
    } else if (periodType === 'year') {
           periodHtml += `
            <label for="filterYear">Năm:</label>
            <select id="filterYear" onchange="loadRevenueReport()">
                ${yearOptions}
            </select>
        `;
    }

    container.innerHTML = periodHtml;
    loadRevenueReport(); 
}

async function loadRevenueReport() {
    const contentElement = document.getElementById('revenueReportContent');
    const periodType = document.getElementById('periodType').value; 
    const roomId = document.getElementById('roomFilter').value; 
    
    contentElement.innerHTML = '<p style="color: gray;">Đang tải dữ liệu báo cáo doanh thu...</p>';

    let periodValue = '';
    let year = document.getElementById('filterYear')?.value || CURRENT_YEAR;

    if (periodType === 'month') {
        const month = document.getElementById('filterMonth')?.value || (new Date().getMonth() + 1);
        periodValue = `${month}/${year}`; 
    } else if (periodType === 'quarter') {
        const quarter = document.getElementById('filterQuarter')?.value || Math.ceil((new Date().getMonth() + 1) / 3);
        periodValue = `Q${quarter}/${year}`; 
    } else if (periodType === 'year') {
        periodValue = year; 
    } else {
        contentElement.innerHTML = '<p style="color: red;">Loại kỳ báo cáo không hợp lệ.</p>';
        return;
    }
    
    try {
        const params = new URLSearchParams({
            periodType: periodType,
            periodValue: periodValue, 
            roomId: roomId 
        });
        
        const revenueData = await fetchData(`/reports/revenue?${params.toString()}`);
        
        if (!revenueData || revenueData.length === 0) {
            contentElement.innerHTML = `<p style="color: #ff9800;">Chưa có dữ liệu doanh thu cho kỳ ${periodValue}.</p>`;
            return;
        }

        let totalRevenue = revenueData.reduce((sum, item) => sum + (item.totalRevenue || 0), 0);
        const totalFormatted = VND_FORMATTER.format(totalRevenue);
        
        let html = `<h3>Tổng Doanh Thu (${periodType.toUpperCase()} - ${periodValue}): ${totalFormatted} VND</h3>`;
        html += `<table class="data-table">`;
        html += `<thead><tr><th>Kỳ/Phòng</th><th>Doanh Thu Phòng</th><th>Doanh Thu Dịch Vụ</th><th>TỔNG DOANH THU</th></tr></thead>`;
        html += '<tbody>';
        
        revenueData.forEach(item => {
            // Giả định backend trả về periodLabel (nếu lọc theo All rooms) hoặc roomName (nếu lọc theo RoomId)
            const periodLabel = item.periodLabel || item.roomName || 'N/A';
            const roomRevenueFormatted = VND_FORMATTER.format(item.roomRevenue || 0);
            const serviceRevenueFormatted = VND_FORMATTER.format(item.serviceRevenue || 0);
            const totalRevenueFormatted = VND_FORMATTER.format(item.totalRevenue || 0);

            html += `<tr>
                <td>${periodLabel}</td>
                <td>${roomRevenueFormatted}</td>
                <td>${serviceRevenueFormatted}</td>
                <td><strong>${totalRevenueFormatted}</strong></td>
            </tr>`;
        });
        html += '</tbody></table>';
        
        contentElement.innerHTML = html;
        
    } catch (error) {
        contentElement.innerHTML = `<p style="color: #f44336;">Lỗi tải báo cáo doanh thu: ${error.message}</p>`;
    }
}


document.addEventListener('DOMContentLoaded', () => {
    showContent('dashboard');
});