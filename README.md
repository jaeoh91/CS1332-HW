# CS 1332 — Data Structures & Algorithms: Homework

> **Georgia Tech** · CS 1332 · Spring 2026  
> Implementations of core data structures and algorithms from scratch in Java, with full unit test suites.

---

# Overview

This repository contains four homework assignments completed as part of **CS 1332: Data Structures and Algorithms** at Georgia Tech. Each assignment required implementing a fundamental data structure or algorithm family **from scratch** — no built-in Java library equivalents allowed (where specified). All implementations were verified against a Gradescope autograder.

**Language:** Java  
**Build Tool:** IntelliJ IDEA (module per assignment)  
**Testing:** JUnit (student-written tests in `StudentTest.java`)

---

# Assignment Breakdown

## HW1 — Linear Data Structures

**Files:** [`ArrayList.java`](HW1/src/ArrayList.java) · [`SinglyLinkedList.java`](HW1/src/SinglyLinkedList.java) · [`SinglyLinkedListNode.java`](HW1/src/SinglyLinkedListNode.java)

| Structure | Key Operations | Complexity Highlights |
|---|---|---|
| `ArrayList<T>` | `addAtIndex`, `removeAtIndex`, `addToFront/Back` | Amortized O(1) append; O(n) shift; dynamic resizing (2×) |
| `SinglyLinkedList<T>` | `addAtIndex`, `removeAtIndex`, `addToFront/Back` | O(1) head insert; O(n) traversal |

**What I implemented:**
- A generic resizable array (`ArrayList`) backed by a raw `Object[]` cast to `T[]`, with automatic capacity doubling
- A singly linked list with a head pointer only (no tail pointer), implementing pointer manipulation for arbitrary-index insertion and removal
- Edge case handling: null data, index out of bounds, empty list operations

---

## HW2 — Binary Search Tree (BST)

**Files:** [`BST.java`](HW2/src/BST.java) · [`BSTNode.java`](HW2/src/BSTNode.java)

| Operation | Complexity |
|---|---|
| `add`, `remove`, `get`, `contains` | O(log n) avg · O(n) worst |
| `preorder`, `inorder`, `postorder` | O(n) |
| `levelorder` (BFS) | O(n) |
| `height` | O(n) |

**What I implemented:**
- Fully recursive BST using the **pointer reinforcement** pattern (methods return the reinforced node)
- `remove` handles all three cases: leaf, one child, and two children (uses **predecessor replacement** via a recursive helper)
- All four traversals: pre/in/post-order (recursive) and level-order (iterative BFS with a `Queue`)
- `BST(Collection<T> data)` constructor for bulk initialization

---

## HW3 — Max-Heap (Priority Queue)

**Files:** [`MaxHeap.java`](HW3/src/MaxHeap.java) · [`MaxHeapChallenge.java`](HW3/src/MaxHeapChallenge.java)

| Operation | Complexity |
|---|---|
| `add` | O(log n) |
| `remove` (extract max) | O(log n) |
| `MaxHeap(ArrayList<T>)` — BuildHeap | **O(n)** |
| `getMax` | O(1) |

**What I implemented:**
- 1-indexed array-backed max-heap (`backingArray[0]` unused for clean parent/child index arithmetic)
- `upHeap` and `downHeap` as private recursive helpers
- **BuildHeap algorithm** in the `MaxHeap(ArrayList<T>)` constructor: copies data into the backing array then runs `downHeap` from the last internal node upward, achieving O(n) construction (vs. O(n log n) for repeated `add` calls)
- Automatic array doubling on overflow; preserves heap invariant throughout

---

## HW4 — Sorting Algorithms

**File:** [`Sorting.java`](HW4/src/Sorting.java)

| Algorithm | Time Complexity | Space | Stable | Adaptive |
|---|---|---|---|---|
| Merge Sort | O(n log n) best/worst | O(n) | ✅ | ❌ |
| Kth Select (QuickSelect) | O(n) avg · O(n²) worst | O(1) | ❌ | ❌ |
| LSD Radix Sort | O(kn) | O(n + k) | ✅ | ❌ |

**What I implemented:**
- **Merge Sort**: out-of-place, stable, splits left-heavy on odd-length arrays; stability guaranteed by `<= 0` comparison on merge
- **kth Select**: randomized pivot selection (`Random` object), in-place Lomuto-style partition; recursively descends only into the relevant half
- **LSD Radix Sort**: handles negative integers using 19 buckets (indices 0–18 for digit range -9 to +9); avoids integer overflow with `long` base; handles `Integer.MIN_VALUE` edge case explicitly; uses `LinkedList` as queues for stability

---

## Testing

Each assignment includes a `StudentTest.java` with JUnit tests covering:
- Standard operation correctness
- Edge cases (empty structures, null inputs, single elements)
- Exception throwing behavior

---

## Repo Structure

```
HW/
├── HW1/src/
│   ├── ArrayList.java
│   ├── SinglyLinkedList.java
│   ├── SinglyLinkedListNode.java
│   └── StudentTest.java
├── HW2/src/
│   ├── BST.java
│   ├── BSTNode.java
│   └── StudentTest.java
├── HW3/src/
│   ├── MaxHeap.java
│   ├── MaxHeapChallenge.java
│   └── StudentTest.java
└── HW4/src/
    ├── Sorting.java
    └── StudentTest.java
```
