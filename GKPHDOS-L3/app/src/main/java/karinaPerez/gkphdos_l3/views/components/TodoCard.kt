package karinaPerez.gkphdos_l3.views.components

import android.R
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import karinaPerez.gkphdos_l3.data.Todo
import karinaPerez.gkphdos_l3.data.dateToString

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoCard(
    todo: Todo,
    modifier: Modifier = Modifier,
    onDelete: (Int) -> Unit,
    onClick: (Todo) -> Unit
) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .border(1.dp, Color.Black, RoundedCornerShape(12.dp))
            .background(
                if (todo.isDone) Color.LightGray else todo.color, RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = todo.title, fontWeight = FontWeight.SemiBold)
        Text(
            text = todo.description, maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth(0.5f)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = dateToString(todo.forDate), fontSize = 14.sp)
            Checkbox(checked = todo.isDone, onCheckedChange = {
                onClick(todo)
            })
        }
        Box(
            modifier = Modifier
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(100.dp))
                .size(25.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "Delete todo",
                modifier = Modifier
                    .width(16.dp)
                    .clickable {
                        onDelete(todo.id)
                    },
                colorFilter = ColorFilter.tint(Color.Black)
            )
        }
    }
}